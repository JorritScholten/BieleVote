import { useState } from "react";
import { Form, Button } from "semantic-ui-react";
import { HttpStatusCode } from "axios";

import { emptyForms } from "../../../misc/ApiForms";
import { backendApi, handleLogError } from "../../../misc/ApiMappings";
import { useAuth } from "../../../misc/AuthContext";

export default function UpdateUsernameForm() {
  const { getUser } = useAuth();
  const [formData, setFormData] = useState(emptyForms.updateUsername);

  async function onSubmit(e) {
    e.preventDefault();
    try {
      const response = await backendApi.updateUsername(formData, getUser());
      if (response.status === HttpStatusCode.Ok) {
        setFormData(emptyForms.updateUsername);
      }
    } catch (error) {
      handleLogError(error);
    }
  }

  return (
    <Form onSubmit={onSubmit} size="small">
      <Form.Input
        label="Change username: "
        name="newUsername"
        placeholder="New username"
        value={formData.newUsername}
        onChange={(e) =>
          setFormData({ ...formData, newUsername: e.target.value })
        }
      />
      <Button type="submit" fluid content="Submit" />
    </Form>
  );
}
