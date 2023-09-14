import Header from "../../components/Header";
import ProjectForm from "./components/ProjectForm";

export default function ProjectWritingPage() {
  return (
    <div>
      <Header pageTitle="Propose new project" />
      <div className="flex flex-col px-20 py-10">
        <h1>Propose something for the city</h1>
        <ProjectForm />
      </div>
    </div>
  );
}
