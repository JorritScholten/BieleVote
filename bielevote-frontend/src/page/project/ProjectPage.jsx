import { useState, useEffect } from "react";
import { useParams, Link } from "react-router-dom";
import { IoReturnDownBack } from "react-icons/io5";
import {
  Container,
  Icon,
  Placeholder,
  Progress,
  Header as SemanticHeader,
} from "semantic-ui-react";
import DOMPurify from "dompurify";

import Header from "../../components/Header";
import ProjectVote from "./components/ProjectVote";
import { emptyForms, projectStatus } from "../../misc/ApiForms";
import { backendApi } from "../../misc/ApiMappings";
import { formatDate } from "../../components/Utils";

export default function ProjectPage() {
  const [project, setProject] = useState(emptyForms.projectInfoDTO);
  const [version, setVersion] = useState(0);
  const { projectId } = useParams();

  useEffect(() => {
    fetchProject(projectId);
  }, [projectId, version]);

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
        {renderProject(project)}
        <div className="m-5 text-center">
          <div className="text-xl ">
            <SemanticHeader as="h3">Votes:</SemanticHeader>
            <div>for: {project.votesFor}</div>
            <div>neutral: {project.votesNeutral}</div>
            <div>against: {project.votesAgainst}</div>
          </div>
          <div className="">
            {project.status === projectStatus.active ? (
              <ProjectVote projectId={projectId} updateVersion={setVersion} />
            ) : (
              <div hidden />
            )}
          </div>
        </div>
      </div>
    </div>
  );
}

function renderProject(project) {
  return (
    <div className="col-span-3 flex flex-col gap-2">
      <SemanticHeader as="h1">{project.title}</SemanticHeader>
      <SemanticHeader.Subheader>
        <Icon name="calendar alternate" /> {formatDate(project.datePublished)}
      </SemanticHeader.Subheader>
      <SemanticHeader.Subheader>
        <Icon name="user" /> {project.author}
      </SemanticHeader.Subheader>
      <div className=" flex flex-row gap-5">
        <div className="text-xl font-semibold">Status: {project.status}</div>
        {/* {project.status === projectStatus.active ? (
          <Progress className="grow" />
        ) : (
          <div hidden />
        )} */}
        <div hidden />
      </div>
      <Container fluid className="text-xl">
        {project.summary}
      </Container>
      {project.content === null || project.content.length === 0 ? (
        placeHolderText(4)
      ) : (
        <div
          className="flex flex-col gap-2 text-justify w-full"
          dangerouslySetInnerHTML={{
            __html: DOMPurify.sanitize(project.content),
          }}
        />
      )}
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
