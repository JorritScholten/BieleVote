import { useState, useEffect } from "react";
import { useParams, Link } from "react-router-dom";
import { IoReturnDownBack } from "react-icons/io5";
import { Button } from "semantic-ui-react";

import Header from "../../components/Header";
import { emptyForms } from "../../misc/ApiForms";
import { backendApi } from "../../misc/ApiMappings";
import { formatDate } from "../../components/Utils";
import { useAuth } from "../../misc/AuthContext";

export default function ProjectPage() {
  const [project, setProject] = useState(emptyForms.projectInfoDTO);
  const { projectId } = useParams();
  const { userIsAuthenticated } = useAuth();

  useEffect(() => {
    fetchProject(projectId);
  }, [projectId]);

  async function fetchProject(projectId) {
    try {
      console.log("performing get");
      const response = await backendApi.getProjectById(projectId);
      console.log(response.data);
      setProject(response.data);
    } catch (error) {
      console.log(error);
    }
  }

  return (
    <div className="flex flex-col gap-2 w-screen">
      <Header pageTitle={project.title} />
      <div className="flex m-auto text-6xl ml-10">
        <Link to={"/projects"}>
          <IoReturnDownBack />
        </Link>
      </div>
      <div className="flex justify-center">
        <div className="flex items-center place-self-center w-3/5">
          <div className="flex flex-col w-3/4">
            <div className="flex items-center justify-between">
              <div className="text-4xl font-bold">{project.title}</div>
            </div>
            <div className="flex flex-row mt-5">
              <div className="mr-3 text-gray-600">
                {formatDate(project.datePublished)}
              </div>
              <div className="flex items-center"></div>
            </div>
            <div className="text-xl font-semibold mt-5 w-4/5">
              Status: {project.status}
            </div>
            <div className="text-gray-600 mt-5">
              {" "}
              Author: {project.author.legalName}
            </div>
            <div className="text-xl mt-5">{project.content}</div>

            <div className="text-xl mt-5">Amount of votes:</div>

            <div className="mt-5">
              {userIsAuthenticated() ? (
                <>
                  <Button className="ui green button ">For</Button>
                  <Button className="ui teal button">Neutral</Button>
                  <Button className="ui red button">Against</Button>
                </>
              ) : (
                <div />
              )}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
