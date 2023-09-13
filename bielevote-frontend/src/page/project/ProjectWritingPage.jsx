import Header from "../../components/Header";
import ProjectForm from "./components/ProjectForm";
import ListProjects from "./components/ListProjects";
import { useEffect, useState } from "react";
import axios from "axios";

function ProjectWritingPage() {
  const [projectsList, setProjectsList] = useState([]);
  const [version, setVersion] = useState(0);
  const incVersion = () => setVersion(version + 1);

  useEffect(() => {
    const getProjectsList = async () => {
      const allProjects = await axios.get(
        "http://localhost:8080/api/v1/project"
      );
      setProjectsList(allProjects.data);
      console.log(allProjects.data);
    };
    getProjectsList();
  }, [version]);

  return (
    <>
      <Header pageTitle="Project" />

      <div className="flex flex-col px-20 py-10">
        <h1>Propose something for the city</h1>
        <ProjectForm incrementDataVersion={incVersion} />
        <ListProjects
          projectsList={projectsList}
          limit={30}
          incrementDataVersion={incVersion}
        />
      </div>
    </>
  );
}

export default ProjectWritingPage;
