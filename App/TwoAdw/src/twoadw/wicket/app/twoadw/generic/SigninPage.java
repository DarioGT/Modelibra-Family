package twoadw.wicket.app.twoadw.generic;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;

import twoadw.website.customer.Customer;
import twoadw.wicket.app.TwoadwAppSession;
import twoadw.wicket.app.twoadw.TwoAdwBasePage;
import twoadw.wicket.app.twoadw.transaction.CheckoutPage;
import twoadw.wicket.website.customers.InputCustomerPage;
import twoadw.wicket.website.productcategories.CategoryListPage;

public class SigninPage extends TwoAdwBasePage {
	
  private class SignInForm extends StatelessForm {

    private String wiaPassword;

    private String wiaUsername;
    
    String forwardPage;

    public SignInForm(final String id, String responsePage) {
      super(id);
      setModel(new CompoundPropertyModel(this));
      forwardPage = responsePage;
      add(new TextField("wiaUsername"));
      add(new PasswordTextField("wiaPassword"));
    }

    public String getWiaPassword() {
      return wiaPassword;
    }

    public String getWiaUsername() {
      return wiaUsername;
    }

    @Override
    public final void onSubmit() {
      if (signIn(wiaUsername, wiaPassword)) {
        if (!continueToOriginalDestination()) {
        	Page tempPage = new CategoryListPage();
        	/*
        	if (forwardPage=="Checkout") tempPage = new CheckoutPage();
        	setResponsePage(tempPage);*/
        	
        	setResponsePage(getTwoadwAppSession().getContextPage());
        	
        	//setResponsePage(new CategoryListPage());
        }
      } else {
        error("Unknown username/ password");
      }
    }

    public void setWiaPassword(String password) {
      this.wiaPassword = password;
    }

    public void setWiaUsername(String username) {
      this.wiaUsername = username;
    }

    private boolean signIn(String username, String password) {
      if (username != null && password != null) {
        Customer user = getCustomers().getCodeCustomerCustomer(username.toLowerCase());
        if (user != null) {
          if (user.getPassword().compareToIgnoreCase(password)==0) {
        	  getTwoadwAppSession().setCustomer(user);
            return true;
          }
        }
      }
      return false;
    }
  }

  public SigninPage(String responsePage) {
	add(new Link("register", getModel()) {
		@Override
		public void onClick() {
			
			setResponsePage(new InputCustomerPage(null));
		}
		
	});
    add(new SignInForm("signInForm",responsePage));
    add(new FeedbackPanel("feedback"));
  }
  
}
