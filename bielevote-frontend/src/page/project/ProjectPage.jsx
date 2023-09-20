import { useState, useEffect } from "react";
import { useParams, Link } from "react-router-dom";
import { IoReturnDownBack } from "react-icons/io5";

import Header from "../../components/Header";
import ProjectVote from "./components/ProjectVote";
import { emptyForms } from "../../misc/ApiForms";
import { backendApi } from "../../misc/ApiMappings";
import { formatDate } from "../../components/Utils";
import { Placeholder } from "semantic-ui-react";

export default function ProjectPage() {
  const [project, setProject] = useState(emptyForms.projectInfoDTO);
  const { projectId } = useParams();

  useEffect(() => {
    fetchProject(projectId);
  }, [projectId]);

  async function fetchProject(projectId) {
    try {
      const response = await backendApi.getProjectById(projectId);
      setProject(response.data);
      // console.log(response.data);
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
          <div className="flex flex-col w-3/4 gap-5">
            <div className="flex items-center justify-between">
              <div className="text-4xl font-bold">{project.title}</div>
            </div>
            <div className="flex flex-row  items-center">
              <div className="mr-5 text-gray-600">
                {formatDate(project.datePublished)}
              </div>
              <div className="text-gray-600 "> Author: {project.author}</div>
            </div>
            <div className="text-xl font-semibold  w-4/5">
              Status: {project.status}
            </div>
            <div className="text-xl font-bold">{project.summary}</div>
            {project.content === null ? (
              placeHolderText(4)
            ) : (
              <div className="">{project.content}</div>
            )}
            <div className="text-xl ">
              Amount of votes:
              <div>for: {project.votesFor}</div>
              <div>neutral: {project.votesNeutral}</div>
              <div>against: {project.votesAgainst}</div>
            </div>
            <div className="">
              <ProjectVote />
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

function placeHolderText(amountOfParagraphs) {
  const repeat = Array.from(Array(amountOfParagraphs).keys());
  return (
    <Placeholder fluid>
      {repeat.map((key) => placeHolderParagraph(key))}
    </Placeholder>
  );
}

function placeHolderParagraph(key) {
  return (
    <Placeholder.Paragraph key={key}>
      <Placeholder.Line />
      <Placeholder.Line />
      <Placeholder.Line />
      <Placeholder.Line />
      <Placeholder.Line />
    </Placeholder.Paragraph>
  );
}
