package org.modelibra.wicket.concept.navigation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.ajax.AbstractAjaxTimerBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.PackageResource;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.time.Duration;
import org.modelibra.IEntity;
import org.modelibra.wicket.container.DmPanel;
import org.modelibra.wicket.util.AjaxMinuteCounterPanel;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

/**
 * AjaxEntitySlideshowNavigatePanel is a more elaborate version of
 * AjaxEntitySlideshowPanel. To revisit.
 * 
 * AjaxEntitySlideshowNavigatePanel for navigation
 * trough entities in viewModel. Four links: first, prior, next and last may be
 * used for controlling the navigation. Additionally there is a slide timer,
 * controlled by play and stop links, which uses time (secs) selected in
 * timerUpdateIntervalChoice as its update interval. See getNavigatedPanelId()
 * and getNewPanel(ViewModel viewModel, View view) on how to use this panel.
 * 
 * @author Vedad Kirlic
 * @version 2008-02-07
 */
@SuppressWarnings("serial")
public abstract class AjaxEntitySlideshowNavigatePanel extends DmPanel {

	private View view;

	private ViewModel viewModel;

	// should we add protected method for users to provide their own list of
	// duration values?
	protected List<Integer> timerUpdateIntervalChoiceList = Arrays.asList(5,
			10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60);

	private Integer slideTimerUpdateInterval = 5;

	private IEntity<?> currentEntity;

	private SlideTimer slideTimer;

	// if slide show continues from last to first entity
	private Boolean repeat = false;

	private AjaxLink playLink;

	private AjaxLink stopLink;

	// list of stopped timers. Each timer register itself here when stopped.
	// When current, not stopped, timer listener method is called, it removes
	// timers in this list from AjaxEntitySlideNavigatePanel, and clears the
	// list. Removing of stopped timers need to be done in current timer
	// listener method so it could update its id (based on position in behaviors
	// list) in callback link. Otherwise next callback would be to non existing
	// timer.
	private List<SlideTimer> stoppedTimers = new ArrayList<SlideTimer>();

	public AjaxEntitySlideshowNavigatePanel(final ViewModel viewModel,
			final View view) {
		super(view.getWicketId());
		this.view = view;
		this.viewModel = viewModel;

		setOutputMarkupId(true);

		currentEntity = viewModel.getEntity();

		// Timer update interval choice
		DropDownChoice timerUpdateIntervalChoice = new DropDownChoice(
				"timerDurationChoice", new PropertyModel(this,
						"slideTimerUpdateInterval"),
				timerUpdateIntervalChoiceList);

		timerUpdateIntervalChoice.add(new AjaxFormComponentUpdatingBehavior(
				"onchange") {
			@Override
			protected void onUpdate(final AjaxRequestTarget target) {
				slideTimerUpdateInterval = (Integer) getComponent()
						.getModelObject();
			}
		});
		add(timerUpdateIntervalChoice);

		// Ajax link used to stop (actually ignore) timer
		stopLink = new AjaxLink("stopLink") {
			@Override
			public void onClick(final AjaxRequestTarget target) {
				slideTimer.markStopped();
				stopLink.setVisible(false);
				playLink.setVisible(true);
				target.addComponent(playLink);
				target.addComponent(stopLink);
			}
		};
		stopLink.add(new Image("stop", PackageResource.get(this.getClass().getSuperclass(), "media-playback-stop.png")));		
		add(stopLink.setOutputMarkupId(true)
				.setOutputMarkupPlaceholderTag(true).setVisible(false));

		// Ajax link used to run slide show
		playLink = new AjaxLink("playLink") {
			@Override
			public void onClick(final AjaxRequestTarget target) {
				stopLink.setVisible(true);
				playLink.setVisible(false);
				slideTimer = new SlideTimer();
				AjaxEntitySlideshowNavigatePanel.this.add(slideTimer);
				target.addComponent(AjaxEntitySlideshowNavigatePanel.this);
				// have to manually add javascript to header response since it
				// is not done (in wicket 1.3. beta4) by adding component to
				// request target. see
				// https://issues.apache.org/jira/browse/WICKET-745 which claims
				// that this is fixed in rc2 but it seems not. Have to test it
				// separately.
				target.getHeaderResponse().renderOnLoadJavascript(
						slideTimer.getJsTimeoutCall());

				target.addComponent(playLink);
				target.addComponent(stopLink);
			}
		};
		playLink.add(new Image("play", PackageResource.get(this.getClass().getSuperclass(), "media-playback-start.png")));
		add(playLink.setOutputMarkupId(true)
				.setOutputMarkupPlaceholderTag(true));

		// CheckBox to control
		CheckBox repeatCheckBox = new CheckBox("repeatCheckBox",
				new PropertyModel(this, "repeat"));

		repeatCheckBox.add(new AjaxFormComponentUpdatingBehavior("onclick") {
			protected void onUpdate(final AjaxRequestTarget target) {
				repeat = (Boolean) getComponent().getModelObject();
			}
		});
		repeatCheckBox.add(new Image("repeat", PackageResource.get(this.getClass().getSuperclass(), "view-refresh.png")));
		add(repeatCheckBox);

		// Link to first slide
		AjaxLink firstLink = new AjaxLink("firstLink") {
			@Override
			public void onClick(final AjaxRequestTarget target) {
				IEntity<?> firstEntity = viewModel.getEntities().first();
				if (firstEntity != null) {
					currentEntity = firstEntity;
					replacePanel(target, viewModel, view);
				}
			}
		};
		add(firstLink);

		// Link to prior slide
		AjaxLink priorLink = new AjaxLink("priorLink") {
			@Override
			public void onClick(final AjaxRequestTarget target) {
				IEntity<?> priorEntity = viewModel.getEntities().prior(
						currentEntity);
				if (priorEntity != null) {
					currentEntity = priorEntity;
					replacePanel(target, viewModel, view);
				}
			}
		};
		add(priorLink);

		// Link to next slide
		AjaxLink nextLink = new AjaxLink("nextLink") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(final AjaxRequestTarget target) {
				IEntity<?> nextEntity = viewModel.getEntities().next(
						currentEntity);
				if (nextEntity != null) {
					currentEntity = nextEntity;
					replacePanel(target, viewModel, view);
				}
			}
		};
		add(nextLink);

		// Link to last slide
		AjaxLink lastLink = new AjaxLink("lastLink") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(final AjaxRequestTarget target) {
				IEntity<?> lastEntity = viewModel.getEntities().last();
				if (lastEntity != null) {
					currentEntity = lastEntity;
					replacePanel(target, viewModel, view);
				}
			}
		};
		add(lastLink);

		// Minute counter
		add(new AjaxMinuteCounterPanel("minuteCounter"));
	}

	/**
	 * AbstractAjaxTimerBehavior implementation used as timer for this panel
	 * 
	 */
	private class SlideTimer extends AbstractAjaxTimerBehavior {

		private static final long serialVersionUID = 1L;

		private boolean stopped = false;

		/**
		 * Creates timer using current slideTimerUpdateInterval value.
		 */
		private SlideTimer() {
			super(Duration.seconds(slideTimerUpdateInterval));
		}

		@Override
		protected void onTimer(final AjaxRequestTarget target) {
			IEntity<?> nextEntity;
			if (!stopped && (nextEntity = getNext()) != null) {
				currentEntity = nextEntity;
				setUpdateInterval(Duration.seconds(slideTimerUpdateInterval));
				replacePanel(target, viewModel, view);
				removeStoppedTimers();
			} else {
				stop();
				stoppedTimers.add(this);
			}
		}

		/**
		 * Marks this timer as stopped, so it could stop() itself on next
		 * callback
		 */
		public void markStopped() {
			this.stopped = true;
		}

		/**
		 * Gets javascript timeout call string. Current value of
		 * slideTimerUpdateInterval is used
		 * 
		 * @return javascript timeout call string
		 */
		public String getJsTimeoutCall() {
			return getJsTimeoutCall(Duration.seconds(slideTimerUpdateInterval));
		}
	}

	/**
	 * Replaces current panel instance with new one (retrieved by getNewPanel).
	 * Sets current entity as entity in viewModel.
	 * 
	 * @param target
	 * @param viewModel
	 * @param view
	 */
	private void replacePanel(final AjaxRequestTarget target,
			final ViewModel viewModel, final View view) {
		Panel navigatedPanel = (Panel) getParent().get(getNavigatedPanelId());

		viewModel.setEntity(currentEntity);
		view.setWicketId(getNavigatedPanelId());

		Panel newPanel = getNavigatedPanel(viewModel, view);
		newPanel.setOutputMarkupId(true);

		navigatedPanel.replaceWith(newPanel);
		target.addComponent(newPanel);
	}

	/**
	 * Gets next entity from entities based on current entity. If current entity
	 * is last entity in entities and repeat flag is set to true, next entity is
	 * first entity from entities.
	 * 
	 * @return next entity
	 */
	private IEntity<?> getNext() {
		IEntity<?> nextEntity = viewModel.getEntities().next(currentEntity);
		if (nextEntity == null && repeat) {
			nextEntity = viewModel.getEntities().first();
		}
		return nextEntity;
	}

	/**
	 * Removes timers registered in stoppedTimers and clears stoppedTimers list
	 */
	private void removeStoppedTimers() {
		for (Iterator<SlideTimer> iterator = stoppedTimers.iterator(); iterator
				.hasNext();) {
			SlideTimer stoppedTimer = iterator.next();
			remove(stoppedTimer);
		}
		stoppedTimers.clear();
	}

	/**
	 * Gets id for navigated panel. Implement this method to provide id of panel
	 * to be navigated. Need to be valid id of one of panels added to common
	 * parent.
	 * 
	 * @return navigated panel id
	 */
	protected abstract String getNavigatedPanelId();

	/**
	 * Gets new instance of navigated panel. Implement this method to provide
	 * new instance of navigated panel that will be used to replace current
	 * instance of navigated panel
	 * 
	 * @param viewModel
	 * @param view
	 * 
	 * @return new instance of navigated panel
	 */
	protected abstract Panel getNavigatedPanel(final ViewModel viewModel,
			final View view);
}
