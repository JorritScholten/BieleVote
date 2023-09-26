import { TestBackendConnection } from "../../components/testing";
import ProjectsPreview from "./components/ProjectsPreview";
import NewsPreview from "./components/NewsPreview";
import { Divider, Grid, HeaderContent, Segment } from "semantic-ui-react";
import { Header } from "../../components";

export default function HomePage() {
  return (
    <div className="flex flex-col gap-5 w-screen">
      <Header pageTitle="Home page" />
      <TestBackendConnection />
      <ProjectsPreview />
      <NewsPreview />
    </div>
  );
}
