import Header from "../../components/Header";
import ProjectForm from "./components/ProjectForm";
import { useEffect, useState } from "react";
import { backendApi } from "../../misc/ApiMappings";

function ProjectWritingPage() {
  const [projectsList, setProjectsList] = useState([]);
  const [version, setVersion] = useState(0);
  const incVersion = () => setVersion(version + 1);

  useEffect(() => {
    const getProjectsList = async () => {
      const allProjects = await backendApi.getAllProjects();
      setProjectsList(allProjects.data);
    };
    getProjectsList();
  }, [version]);

  return (
    <>
      <Header pageTitle="Project" />

      <div className="flex flex-col px-20 py-10">
        <h1>Propose something for the city</h1>
        <ProjectForm incrementDataVersion={incVersion} />
      </div>
    </>
  );
}

export default ProjectWritingPage;
