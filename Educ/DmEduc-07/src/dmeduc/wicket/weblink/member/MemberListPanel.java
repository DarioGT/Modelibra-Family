package dmeduc.wicket.weblink.member;

import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;

import dmeduc.weblink.member.Member;

/**
 * Member list panel.
 * 
 * @author Dzenan Ridjanovic
 * @version 2008-09-26
 */
@SuppressWarnings("serial")
public class MemberListPanel extends Panel {

	public MemberListPanel(final String wicketId, final List<Member> memberList) {
		super(wicketId);
		add(new MemberList("memberList", memberList));
	}

	/**
	 * Member list view as an inner class.
	 */
	private class MemberList extends ListView {

		public MemberList(final String wicketId, final List<Member> memberList) {
			super(wicketId, memberList);
		}

		protected void populateItem(final ListItem item) {
			Member member = (Member) item.getModelObject();
			String lastName = member.getLastName().toUpperCase();
			String firstName = member.getFirstName();
			String memberName = lastName + ", " + firstName;
			item.add(new Label("memberName", memberName));
		}

	}

}
