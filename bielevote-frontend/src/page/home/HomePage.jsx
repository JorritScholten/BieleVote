import DisplayAccountBalance from "../../components/DisplayAccountBalance";
import Header from "../../components/Header";
import PrintUserInfo from "../../components/PrintUserInfo";
import TestBackendConnection from "../../components/TestBackendConnection";

export default function HomePage() {
  return (
    <div className="flex flex-col gap-2 w-screen">
      <Header pageTitle="Home page" />
      <TestBackendConnection />
      <DisplayAccountBalance />
      <PrintUserInfo />
    </div>
  );
}
