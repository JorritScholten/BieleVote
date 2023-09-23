import { Header } from "../../components";
import { TestBackendConnection } from "../../components/testing";

export default function HomePage() {
  return (
    <div className="flex flex-col gap-5 w-screen">
      <Header pageTitle="Home page" />
      <TestBackendConnection />
    </div>
  );
}
