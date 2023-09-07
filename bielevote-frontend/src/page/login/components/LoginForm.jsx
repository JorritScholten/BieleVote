import { useState } from "react";
import { loginForm } from "../../../misc/ApiForms";
import { Form, Button } from "semantic-ui-react";
import { backendApi } from "../../../misc/ApiMappings";

export default function LoginForm() {
  const [formData, setFormData] = useState(loginForm.emptyForm);

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
    setFormData(loginForm.emptyForm);
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
