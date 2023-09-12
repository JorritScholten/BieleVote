import Header from "../../components/Header";
import ProjectForm from "./components/ProjectForm";
import ProjectListingComponent from "./components/ProjectListingComponent";

function ProjectWritingPage() {
  return (
    <>
      <Header pageTitle="Project" />

      <div className="flex flex-col px-20 py-10">
        <h1>Propose something for the city</h1>
        <ProjectForm />
        <ProjectListingComponent />
      </div>

    </>
  );
}

export default ProjectWritingPage;
