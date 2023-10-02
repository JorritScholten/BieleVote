import PropTypes from "prop-types";
import { Button, Form, Message } from "semantic-ui-react";
import { useState } from "react";

import { Header } from "../../components";
import { emptyForms } from "../../misc/ApiForms";
import { backendApi, handleLogError } from "../../misc/ApiMappings";
import { HttpStatusCode } from "axios";

export default function CreateAccountPage() {
  const [newAccount, setNewAccount] = useState(emptyForms.newAccount);
  const [hideMessage, setHideMessage] = useState(true);

  // async function onSubmit(e) {
  //   e.preventDefault();
  //   setNewAccount(emptyForms.newAccount);
  //   setHideMessage(false);
  // }
  async function onSubmit(e) {
    e.preventDefault();
    try {
      console.table(newAccount);
      const response = await backendApi.postAccountRequest(newAccount);
      if (response.status === HttpStatusCode.Created) {
        setNewAccount(emptyForms.newAccount);
        setHideMessage(false);
      }
    } catch (error) {
      handleLogError(error);
      alert("Something went wrong, please try again");
    }
  }

  return (
    <div className="h-screen flex flex-col">
      <Header pageTitle="Create new account" />
      <div className="px-20 py-10 h-full">
        <div className="font-bold text-3xl mb-5">
          Please enter your personal details:
        </div>

        <Form onSubmit={onSubmit}>
          <Form.Input
            label="Username:"
            name="username"
            value={newAccount.username}
            onChange={(e) =>
              setNewAccount({ ...newAccount, username: e.target.value })
            }
            placeholder="Username"
            required={true}
            disabled={!hideMessage}
          />
          <Form.Input
            label="First name and surname:"
            name="legalName"
            value={newAccount.legalName}
            onChange={(e) =>
              setNewAccount({ ...newAccount, legalName: e.target.value })
            }
            placeholder="First name and surname"
            required={true}
            disabled={!hideMessage}
          />
          <Form.Input
            label="Telephone number:"
            name="phone"
            value={newAccount.phone}
            onChange={(e) =>
              setNewAccount({ ...newAccount, phone: e.target.value })
            }
            pattern="[0-9]{8,}"
            placeholder="Telephone number"
            required={true}
            disabled={!hideMessage}
          />
          <Button type="submit" content="Submit" disabled={!hideMessage} />
        </Form>
        <Message
          hidden={hideMessage}
          success
          header="Your request has been received."
          content="We will now verifiy your personal details. "
        />
      </div>
    </div>
  );
}

CreateAccountPage.propTypes = {};
