package org.ieducnews.model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;

import javax.mail.internet.AddressException;

import org.ieducnews.model.concept.contribution.Comment;
import org.ieducnews.model.concept.contribution.Comments;
import org.ieducnews.model.concept.contribution.Question;
import org.ieducnews.model.concept.contribution.Submission;
import org.ieducnews.model.concept.contribution.Submissions;
import org.ieducnews.model.concept.contribution.WebLink;
import org.ieducnews.model.concept.member.Member;
import org.ieducnews.model.concept.member.Members;
import org.ieducnews.model.concept.member.Vote;
import org.ieducnews.model.concept.member.Member.SecurityRole;
import org.ieducnews.model.config.ModelProperties;
import org.ieducnews.model.type.Email;

public class DomainModel implements Serializable {

	private static final long serialVersionUID = 1;

	private File file;

	private Members members = new Members();

	private Submissions submissions = new Submissions();

	private Comments comments = new Comments();

	public DomainModel() {
		ModelProperties modelProperties = new ModelProperties(
				ModelProperties.class);
		createFile(modelProperties);
	}

	public DomainModel(ModelProperties modelProperties) {
		createFile(modelProperties);
	}

	private void createFile(ModelProperties modelProperties) {
		file = new File(modelProperties.getFilePath());
	}

	private void initMembers() {
		try {
			Member member01 = new Member();
			member01.setLastName("Ridjanovic");
			member01.setFirstName("Dzenan");
			member01.setEmail(new Email("dzenanr@gmail.com"));
			member01.setAccount("dzenanr");
			member01.setPassword("dr");
			member01.setRole(SecurityRole.ADMIN);
			member01.setApproved(true);

			Member member02 = new Member();
			member02.setLastName("Daneault");
			member02.setFirstName("Pascal");
			member02.setEmail(new Email("pascal.daneault@gmail.com"));
			member02.setAccount("pascald");
			member02.setPassword("pd");
			member02.setRole(SecurityRole.ADMIN);
			member02.setApproved(true);

			Member member03 = new Member();
			member03.setLastName("Ridjanovic");
			member03.setFirstName("Amra");
			member03.setEmail(new Email("amrar@gmail.com"));
			member03.setAccount("amrar");
			member03.setPassword("ar");

			members.add(member01);
			members.add(member02);
			members.add(member03);
		} catch (AddressException e) {
			System.out.println("Not a valid email: " + e);
		}
	}

	private void initSubmissions() {
		try {
			Member dzenanr = getMembers().retrieveByAccount("dzenanr");
			Member pascald = getMembers().retrieveByAccount("pascald");

			if (dzenanr != null && pascald != null) {
				WebLink webLink01 = new WebLink(dzenanr);
				webLink01.setName("Hacker News");
				webLink01.setLink(new URL("http://news.ycombinator.com/"));
				if (submissions.add(webLink01)) {
					dzenanr.getSubmissions().add(webLink01);
					webLink01.incrementPoints();
					dzenanr.incrementKarma();
				}

				WebLink webLink02 = new WebLink(dzenanr);
				webLink02.setName("TechCrunch");
				webLink02.setLink(new URL("http://www.techcrunch.com/"));
				if (submissions.add(webLink02)) {
					dzenanr.getSubmissions().add(webLink02);
					webLink02.incrementPoints();
					dzenanr.incrementKarma();
				}

				WebLink webLink03 = new WebLink(pascald);
				webLink03.setName("Jane's E-Learning Pick");
				webLink03.setLink(new URL("http://janeknight.typepad.com/"));
				if (submissions.add(webLink03)) {
					pascald.getSubmissions().add(webLink03);
					webLink03.incrementPoints();
					pascald.incrementKarma();
				}

				WebLink webLink04 = new WebLink(dzenanr);
				webLink04.setName("Web Standards Curriculum");
				webLink04
						.setLink(new URL(
								"http://dev.opera.com/articles/view/1-introduction-to-the-web-standards-cur/"));
				if (submissions.add(webLink04)) {
					dzenanr.getSubmissions().add(webLink04);
					webLink04.incrementPoints();
					dzenanr.incrementKarma();
				}

				WebLink webLink05 = new WebLink(pascald);
				webLink05.setName("Free Online Classes");
				webLink05.setLink(new URL(
						"http://www.guidetoonlineschools.com/online-classes"));
				if (submissions.add(webLink05)) {
					pascald.getSubmissions().add(webLink05);
					webLink05.incrementPoints();
					pascald.incrementKarma();
				}

				Question question01 = new Question(dzenanr);
				question01
						.setName("Rules for understanding the ranking of Hacker News");
				question01
						.setText("Why the ranking is not explained in Guidelines?");
				if (submissions.add(question01)) {
					dzenanr.getSubmissions().add(question01);
					question01.incrementPoints();
					dzenanr.incrementKarma();
				}
			}
		} catch (MalformedURLException e) {
			System.out.println("Not a valid URL: " + e);
		}
	}

	private void initComments() {
		Member dzenanr = getMembers().retrieveByAccount("dzenanr");
		Member pascald = getMembers().retrieveByAccount("pascald");
		Submission janePick = getSubmissions().retrieveByName(
				"Jane's E-Learning Pick");
		if (dzenanr != null && pascald != null && janePick != null) {
			Comment comment01 = new Comment(dzenanr, janePick);
			comment01.setText("Is there only one pick per day?");
			if (comments.add(comment01)) {
				dzenanr.getComments().add(comment01);
				janePick.getComments().add(comment01);
				comment01.incrementPoints();
			}

			Comment comment02 = new Comment(pascald, janePick, comment01);
			comment02.setText("It may be more than one.");
			if (comments.add(comment02)) {
				pascald.getComments().add(comment02);
				janePick.getComments().add(comment02);
				comment01.getReplies().add(comment02);
				comment02.incrementPoints();
			}
		}
	}

	private void initVotes() {
		Member dzenanr = getMembers().retrieveByAccount("dzenanr");
		Submission janePick = getSubmissions().retrieveByName(
				"Jane's E-Learning Pick");
		if (dzenanr != null && janePick != null) {
			Vote vote01 = new Vote(dzenanr, janePick);
			if (dzenanr.getVotes().add(vote01)) {
				janePick.incrementPoints();
			}
		}
	}

	public Members getMembers() {
		return members;
	}

	public Submissions getSubmissions() {
		return submissions;
	}

	public Comments getComments() {
		return comments;
	}

	public DomainModel load() {
		try {
			if (!file.exists()) {
				initMembers();
				initSubmissions();
				initComments();
				initVotes();
				save();
			}
			BufferedInputStream buffer = new BufferedInputStream(
					new FileInputStream(file));
			ObjectInput i = new ObjectInputStream(buffer);
			return (DomainModel) i.readObject();

		} catch (ClassNotFoundException e1) {
			throw new RuntimeException(e1);
		} catch (IOException e2) {
			throw new RuntimeException(e2);
		}
	}

	public void save() {
		try {
			if (!file.exists()) {
				file.createNewFile();
				System.out.println("Model file created: "
						+ file.getAbsolutePath());
			}
			BufferedOutputStream buffer = new BufferedOutputStream(
					new FileOutputStream(file));
			ObjectOutput o = new ObjectOutputStream(buffer);
			o.writeObject(this);
			buffer.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
