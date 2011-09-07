/*
 * Modelibra
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package course.wicket.lecture.slide;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.panel.Panel;
import org.modelibra.wicket.concept.navigation.AjaxEntitySlideshowPanel;
import org.modelibra.wicket.concept.navigation.AjaxFallbackEntityNavigatePanel;
import org.modelibra.wicket.util.AjaxMinuteCounterPanel;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;

import course.lecture.slide.Slide;
import course.lecture.slide.Slides;

/**
 * Entity display slide page.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-01-19
 */
public class EntityDisplaySlidePage extends WebPage {

	private static final long serialVersionUID = 222010L;

	public EntityDisplaySlidePage(ViewModel viewModel, View view) {
		add(homePageLink("homePage"));

		ViewModel slideViewModel = new ViewModel();
		slideViewModel.copyPropertiesFrom(viewModel);

		View slideView = new View();
		slideView.copyPropertiesFrom(view);
		slideView.setWicketId("slideDisplaySlidePanel");

		Slides slides = (Slides) viewModel.getEntities();
		Slide slide = (Slide) viewModel.getEntity();
		Slide firstSlide = (Slide) slides.first();
		Panel slideDisplaySlidePanel;
		if (firstSlide == null) {
			slideDisplaySlidePanel = new Panel("slideDisplaySlidePanel");
			slideDisplaySlidePanel.setVisible(false);
		} else {
			if (slide == null) {
				Slides orderedSlides = slides.getSlidesOrderedByNumber(true);
				slideViewModel.setEntities(orderedSlides);
				slideViewModel.setEntity(firstSlide);
			} else {
				slideViewModel.setEntities(slides);
				slideViewModel.setEntity(slide);
			}
			slideDisplaySlidePanel = new SlideDisplaySlidePanel(slideViewModel,
					slideView);
			slideDisplaySlidePanel.setOutputMarkupId(true);
		}
		add(slideDisplaySlidePanel);

		// Ajax slideshow.
		ViewModel slideshowViewModel = new ViewModel();
		slideshowViewModel.copyPropertiesFrom(slideViewModel);

		View slideshowView = new View();
		slideshowView.copyPropertiesFrom(slideView);
		slideshowView.setWicketId("slideshow");

		AjaxEntitySlideshowPanel ajaxEntitySlideshowPanel = new AjaxEntitySlideshowPanel(
				slideshowViewModel, slideshowView) {
			static final long serialVersionUID = 1L;

			@Override
			protected String getNavigatedPanelId() {
				return "slideDisplaySlidePanel";
			}

			@Override
			protected Panel getNavigatedPanel(final ViewModel viewModel,
					final View view) {
				return new SlideDisplaySlidePanel(viewModel, view);
			}
		};
		add(ajaxEntitySlideshowPanel);

		// Ajax minute counter.
		add(new AjaxMinuteCounterPanel("minuteCounter"));

		// Ajax slide navigator uses the same ViewModel as
		// AjaxEntitySlideshowPanel.

		View navigateView = new View();
		navigateView.copyPropertiesFrom(slideshowView);
		navigateView.setWicketId("navigator");

		AjaxFallbackEntityNavigatePanel ajaxEntitySlideNavigatePanel = new AjaxFallbackEntityNavigatePanel(
				slideshowViewModel, navigateView) {
			static final long serialVersionUID = 1L;

			@Override
			protected String getNavigatedPanelId() {
				return "slideDisplaySlidePanel";
			}

			@Override
			protected Panel getNavigatedPanel(final ViewModel viewModel,
					final View view) {
				return new SlideDisplaySlidePanel(viewModel, view);
			}
		};
		add(ajaxEntitySlideNavigatePanel);
	}

}
