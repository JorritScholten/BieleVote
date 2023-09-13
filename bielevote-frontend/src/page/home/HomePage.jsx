import Header from "../../components/Header";
import { Link } from "react-router-dom";
import TestBackendConnection from "../../components/TestBackendConnection";

export default function HomePage() {
  return (
    <div className="flex flex-col gap-2 w-screen">
      <Header pageTitle="Home page" />
      <TestBackendConnection />
    </div>
  );
}
