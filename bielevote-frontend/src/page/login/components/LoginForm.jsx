import { useState } from "react";
import { loginForm } from "../../../misc/ApiForms";
import { Form } from "react-router-dom";
import { Button } from "semantic-ui-react";
// import { backendApi } from "../../../misc/ApiMappings";

export default function LoginForm() {
  const [formData, setFormData] = useState({
    username: "",
    password: "",
  });

  const handleFormChange = (e, { name, value }) => {
    if (e.target.inputMode === "numeric") {
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
    console.log("handling submit");
    // backendApi.login(formData);
  };

  return (
    <>
      {/* <Form onSubmit={handleSubmit}>
        <Form.Input
          name="username"
          placeholder="Username"
          value={formData.username}
          //   onChange={(e) => handleFormChange(e, setFormData)}
          pattern="[\S]+"
          required={true}
        />
        <Form.Input
          name="password"
          placeholder="Password"
          value={formData.password}
          // onChange={(e) => handleFormChange(e, setFormData)}
          pattern="[\S]+"
          required={true}
        />
        <Button type="submit" content="Login" />
      </Form> */}
      {/* <form onSubmit={handleSubmit}>
        <input
          name="username"
          placeholder="Username"
          value={formData.username}
          onChange={handleFormChange}
          pattern="[\S]+"
          required
        />
        <input
          name="password"
          placeholder="Password"
          value={formData.password}
          onChange={handleFormChange}
          pattern="[\S]+"
          required
        />
        <button type="submit">Login</button>
      </form> */}
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
    </>
  );
}
