import { useState } from "react";
import { emptyForms } from "../misc/ApiForms";
import { Form, Button } from "semantic-ui-react";
import { backendApi, handleLogError, parseJwt } from "../misc/ApiMappings";
import { useAuth } from "../misc/AuthContext";
import { useNavigate } from "react-router-dom";

export default function LoginForm() {
  const navigate = useNavigate();
  const Auth = useAuth();
  const [formData, setFormData] = useState(emptyForms.login);
  const [showFields, setShowFields] = useState(false);

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
    <Form onSubmit={handleSubmit} size="tiny" className="w-full">
      <Form.Input
        name="username"
        placeholder="Username"
        value={formData.username}
        onChange={handleFormChange}
        required={true}
        className={showFields ? "" : "hidden"}
      />
      <Form.Input
        name="password"
        placeholder="Password"
        value={formData.password}
        onChange={handleFormChange}
        required={true}
        type="password"
        className={showFields ? "" : "hidden"}
      />
      {showFields ? (
        <Button.Group widths={2} size="tiny">
          <Button type="submit" color="green" content="Login" />
          <Button
            type="button"
            color="orange"
            onClick={() => setShowFields(false)}
            content="Hide"
          />
        </Button.Group>
      ) : (
        <Button.Group widths={2} size="tiny">
          <Button
            type="button"
            color="green"
            onClick={() => setShowFields(true)}
            content="Login"
          />
          <Button
            type="button"
            color="grey"
            onClick={() => navigate("/new-account")}
            content="Create account"
          />
        </Button.Group>
      )}
    </Form>
  );
}
