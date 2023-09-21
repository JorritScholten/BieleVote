import { useState, useEffect } from "react";
import { useParams, Link } from "react-router-dom";
import { IoReturnDownBack } from "react-icons/io5";
import {
  Container,
  Icon,
  Placeholder,
  Header as SemanticHeader,
} from "semantic-ui-react";

import Header from "../../components/Header";
import ProjectVote from "./components/ProjectVote";
import { emptyForms } from "../../misc/ApiForms";
import { backendApi } from "../../misc/ApiMappings";
import { formatDate } from "../../components/Utils";

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
    } catch (error) {
      console.log(error);
    }
  }

  return (
    <div className="flex flex-col gap-5 w-screen">
      <Header pageTitle={project.title} />
      <div className="grid grid-cols-5 items-start">
        <Link to={"/projects"} className=" text-6xl font-bold m-5">
          <IoReturnDownBack />
        </Link>
        <div className="col-span-3 flex flex-col gap-2">
          <SemanticHeader as="h1">{project.title}</SemanticHeader>
          <div>
            <Icon name="calendar alternate" />{" "}
            {formatDate(project.datePublished)}
          </div>
          <div>
            <Icon name="user" /> {project.author}
          </div>
          <div className="text-xl font-semibold  w-4/5">
            Status: {project.status}
          </div>
          <div className="text-xl ">{project.summary}</div>
          {project.content === null ? (
            placeHolderText(4)
          ) : (
            <Container text> {project.content}</Container>
          )}
        </div>
        <div className="m-5 text-center">
          <div className="text-xl ">
            <SemanticHeader as="h3">Votes:</SemanticHeader>
            <div>for: {project.votesFor}</div>
            <div>neutral: {project.votesNeutral}</div>
            <div>against: {project.votesAgainst}</div>
          </div>
          <div className="">
            <ProjectVote projectId={projectId} />
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
