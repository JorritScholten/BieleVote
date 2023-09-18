import Header from "../../components/Header";
import ProjectForm from "./components/ProjectForm";

export default function ProjectWritingPage() {
  return (
    <div className="h-screen flex flex-col">
      <Header pageTitle="Propose new project" />
      <div className="px-20 py-10 h-full">
        <ProjectForm />
      </div>
    </div>
  );
}
