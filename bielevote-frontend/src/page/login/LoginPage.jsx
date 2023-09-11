import Header from "../../components/Header";
import LoginForm from "./components/LoginForm";
import TestBackendConnection from "../../components/TestBackendConnection";
import PrintUserInfo from "../../components/PrintUserInfo";

export default function LoginPage() {
  return (
    <div>
      <Header pageTitle="Login" />
      <LoginForm />
      <TestBackendConnection />
      <PrintUserInfo />
    </div>
  );
}
