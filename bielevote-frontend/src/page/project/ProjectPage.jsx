import { useState, useEffect } from "react";
import { useParams, Link } from "react-router-dom";
import { IoReturnDownBack } from "react-icons/io5";
import {
  Card,
  Container,
  Icon,
  Item,
  Label,
  Placeholder,
  Progress,
  Header as SemanticHeader,
} from "semantic-ui-react";

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
        {renderProjectAsDiv(project)}
        {/* {renderProjectAsCard(project)} */}
        {/* {renderProjectAsItem(project)} */}
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

function renderProjectAsItem(project) {
  return (
    <Item className="col-span-3" fluid>
      <Item.Content>
        {/* <Item.Header as={SemanticHeader}>{project.title}</Item.Header> */}
        <SemanticHeader as="h1">{project.title}</SemanticHeader>
        <Item.Meta>
          <Icon name="calendar alternate" /> {formatDate(project.datePublished)}
        </Item.Meta>
        <Item.Meta>
          <Icon name="user" /> {project.author}
        </Item.Meta>
        <Item.Meta className="flex flex-row gap-5">
          <div className="text-xl font-semibold">Status: {project.status}</div>
          {project.status === projectStatus.active ? (
            <Progress className="grow" />
          ) : (
            <div hidden />
          )}
          <div hidden />
        </Item.Meta>
        <Item.Description className="text-xl">
          {project.summary}
        </Item.Description>
        <Item.Meta />
        {project.content === null || project.content.length === 0 ? (
          placeHolderText(4)
        ) : (
          <Container text>{project.content}</Container>
        )}
      </Item.Content>
    </Item>
  );
}

function renderProjectAsDiv(project) {
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
        {project.status === projectStatus.active ? (
          <Progress className="grow" />
        ) : (
          <div hidden />
        )}
        <div hidden />
      </div>
      <Container fluid className="text-xl">
        {project.summary}
      </Container>
      {project.content === null || project.content.length === 0 ? (
        placeHolderText(4)
      ) : (
        <Container fluid>{project.content}</Container>
      )}
    </div>
  );
}

function renderProjectAsCard(project) {
  return (
    <Card className="col-span-3" fluid>
      <Card.Content>
        <Card.Header as="h1">{project.title}</Card.Header>
        <Card.Meta>
          <Icon name="calendar alternate" /> {formatDate(project.datePublished)}
        </Card.Meta>
        <Card.Meta>
          <Icon name="user" /> {project.author}
        </Card.Meta>
        <Card.Meta>
          <div className="text-xl font-semibold">Status: {project.status}</div>
          {project.status === projectStatus.active ? (
            <Progress className="grow" />
          ) : (
            <div hidden />
          )}
          <div hidden />
        </Card.Meta>
        <Card.Description className="text-xl">
          {project.summary}
        </Card.Description>
        <Card.Meta />
        <Card.Description>
          {project.content === null || project.content.length === 0 ? (
            placeHolderText(4)
          ) : (
            <Container text>{project.content}</Container>
          )}
        </Card.Description>
      </Card.Content>
    </Card>
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
