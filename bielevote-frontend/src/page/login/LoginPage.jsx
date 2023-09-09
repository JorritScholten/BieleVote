import Header from "../../components/Header";
import LoginForm from "./components/LoginForm";
import TestBackendConnection from "../../components/TestBackendConnection";

export default function LoginPage() {
  return (
    <div>
      <Header pageTitle="Login" />
      <LoginForm />
      <TestBackendConnection />
    </div>
  );
}
