import { TestBackendConnection } from "../../components/testing";
import ProjectsPreview from "./components/ProjectsPreview";
import NewsPreview from "./components/NewsPreview";
import { Header } from "../../components";
import Leaderboard from "./components/Leaderboard";
import { Table } from "semantic-ui-react";

export default function HomePage() {
  return (
    <div className="flex flex-col gap-5 w-screen">
      <Header pageTitle="Home page" />
      {/* <TestBackendConnection /> */}
      <div className="">
        <Table celled columns={2}>
          <Table.Header>
            <Table.Row>
              <Table.HeaderCell>Recent Projects</Table.HeaderCell>
              <Table.HeaderCell>Recent News</Table.HeaderCell>
            </Table.Row>
          </Table.Header>
          <Table.Body>
            <Table.Row>
              <Table.Cell>
                <ProjectsPreview />
              </Table.Cell>
              <Table.Cell>
                <NewsPreview />
              </Table.Cell>
            </Table.Row>
          </Table.Body>
          <Table.Footer>
            <Leaderboard />
          </Table.Footer>
        </Table>
      </div>
    </div>
  );
}
