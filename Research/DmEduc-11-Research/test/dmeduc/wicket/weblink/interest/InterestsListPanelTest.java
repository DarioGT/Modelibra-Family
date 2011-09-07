package dmeduc.wicket.weblink.interest;

import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.util.tester.TestPanelSource;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.BeforeClass;
import org.junit.Test;
import org.modelibra.wicket.view.View;
import org.modelibra.wicket.view.ViewModel;
import org.modelibra.wicket.widget.LabelPanel;

import dmeduc.weblink.WebLink;
import dmeduc.weblink.interest.Interest;
import dmeduc.weblink.interest.Interests;
import dmeduc.wicket.app.DmEducApp;

/**
 * InterestsListPanel tests.
 * 
 * @author Vedad Kirlic
 * @version 2008-09-30
 */
@SuppressWarnings("serial")
public class InterestsListPanelTest {

	private static WicketTester tester;

	private static Interests interests;

	@BeforeClass
	public static void prepareTester() {
		DmEducApp dmEducApp = new DmEducApp();
		final WebLink webLink = dmEducApp.getDmEduc().getWebLink();
		interests = webLink.getMembers().first().getInterests();

		tester = new WicketTester(dmEducApp);
		tester.startPanel(new TestPanelSource() {
			@Override
			public Panel getTestPanel(String panelId) {
				ViewModel memberIntersestsModel = new ViewModel();
				memberIntersestsModel.setModel(webLink);
				memberIntersestsModel.setEntities(interests);

				View memberIntersestsView = new View();
				memberIntersestsView.setWicketId(panelId);

				return new InterestListPanel(memberIntersestsModel,
						memberIntersestsView);
			}
		});

	}

	@Test
	public void containInterestList() {
		tester.assertComponent("panel:interestList", InterestList.class);
	}

	@Test
	public void useInterestListModel() {
		List<Interest> expectedList = interests.getList();
		tester.assertListView("panel:interestList", expectedList);
	}

	@Test
	public void containCategoryNameComponent() {
		tester
				.assertComponent("panel:interestList:0:categoryName",
						Label.class);
	}

	@Test
	public void containCategoryNameLabel() {
		Interest interest = interests.first();
		String name = interest.getCategory().getName().toUpperCase();

		tester.assertLabel("panel:interestList:0:categoryName", name);
	}

	@Test
	public void containInterestDescriptionComponent() {
		tester.assertComponent("panel:interestList:0:interestDescription",
				LabelPanel.class);
	}

	@Test
	public void containInterestDescriptionLabelPanel() {
		Interest interest = interests.first();
		String description = interest.getDescription();

		tester.assertLabel(
				"panel:interestList:0:interestDescription:propertyValue",
				description);
	}

}
