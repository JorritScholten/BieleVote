import { useState, useEffect } from "react";
import { useParams, Link, useNavigate } from "react-router-dom";
import { IoReturnDownBack } from "react-icons/io5";
import {
  Button,
  Container,
  Icon,
  Placeholder,
  Progress,
  Header as SemanticHeader,
} from "semantic-ui-react";
import DOMPurify from "dompurify";
import { HttpStatusCode } from "axios";

import Header from "../../components/Header";
import ProjectVote from "./components/ProjectVote";
import { emptyForms, projectStatus } from "../../misc/ApiForms";
import { backendApi } from "../../misc/ApiMappings";
import { formatDate } from "../../components/Utils";
import { useAuth } from "../../misc/AuthContext";
import { accountType } from "../../misc/NavMappings";

export default function ProjectPage() {
  const [project, setProject] = useState(emptyForms.projectInfoDTO);
  const [version, setVersion] = useState(0);
  const { projectId } = useParams();
  const { getUser, getAccountType } = useAuth();
  const navigate = useNavigate();

  useEffect(() => {
    fetchProject(projectId);
  }, [projectId, version]);

  async function fetchProject(projectId) {
    try {
      const response = await backendApi.getProjectById(projectId, getUser());
      setProject(response.data);
    } catch (error) {
      if (error.response.status === HttpStatusCode.Unauthorized) {
        navigate("/projects");
      } else {
        console.log(error);
      }
    }
  }

  async function updateStatus(newStatus) {
    console.log("need to add api mapping: " + newStatus);
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
          {project.status === projectStatus.proposed &&
          getAccountType() === accountType.municipal ? (
            <div className="text-xl ">
              <SemanticHeader as="h3">Permit publication:</SemanticHeader>
              <Button.Group fluid>
                <Button
                  positive
                  onClick={() => updateStatus(projectStatus.active)}
                  content="Allow"
                />
                <Button
                  negative
                  onClick={() => updateStatus(projectStatus.denied)}
                  content="Deny"
                />
              </Button.Group>
            </div>
          ) : (
            <div className="text-xl flex flex-col gap-5">
              <SemanticHeader as="h3">Votes:</SemanticHeader>
              <div>
                <div>for: {project.votesFor}</div>
                <div>neutral: {project.votesNeutral}</div>
                <div>against: {project.votesAgainst}</div>
              </div>
              {project.status === projectStatus.active ? (
                <ProjectVote projectId={projectId} updateVersion={setVersion} />
              ) : (
                <div hidden />
              )}
            </div>
          )}
        </div>
      </div>
    </div>
  );
}

function renderProject(project) {
  return (
    <div className="col-span-3 flex flex-col gap-2">
      {project.title === null || project.title.length === 0 ? (
        <Placeholder>
          <Placeholder.Line />
        </Placeholder>
      ) : (
        <SemanticHeader as="h1">{project.title}</SemanticHeader>
      )}
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
      {project.summary === null || project.summary.length === 0 ? (
        placeHolderText(1)
      ) : (
        <Container fluid className="text-xl">
          {project.summary}
        </Container>
      )}
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
