import { useState } from "react";
import { emptyForms } from "../../../misc/ApiForms";
import { Form, Button } from "semantic-ui-react";
import {
  backendApi,
  handleLogError,
  parseJwt,
} from "../../../misc/ApiMappings";
import { useAuth } from "../../../misc/AuthContext";

export default function LoginForm() {
  const Auth = useAuth();
  const [formData, setFormData] = useState(emptyForms.login);

  const handleFormChange = (e, { inputMode, name, value }) => {
    if (inputMode === "numeric") {
      value = Number.parseInt(value);
      value = value.isNaN ? 0 : value;
    }
    setFormData((prevState) => ({
      ...prevState,
      [name]: value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    backendApi.login(formData);
    try {
      const response = await backendApi.login(formData);
      const { accessToken } = response.data;
      const data = parseJwt(accessToken);
      const authenticatedUser = { data, accessToken };

      Auth.userLogin(authenticatedUser);
      setFormData(emptyForms.login);
    } catch (error) {
      handleLogError(error);
    }
  };

  return (
    <div>
      <Form onSubmit={handleSubmit}>
        <Form.Input
          name="username"
          placeholder="Username"
          value={formData.username}
          onChange={handleFormChange}
          pattern="[\S]+"
          required={true}
        />
        <Form.Input
          name="password"
          placeholder="Password"
          value={formData.password}
          onChange={handleFormChange}
          pattern="[\S]+"
          required={true}
        />
        <Button type="submit" content="Login" />
      </Form>
    </div>
  );
}