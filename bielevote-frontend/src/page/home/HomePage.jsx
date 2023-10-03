import { Icon, Table } from "semantic-ui-react";

import ProjectsPreview from "./components/ProjectsPreview";
import NewsPreview from "./components/NewsPreview";
import { Header } from "../../components";
import Leaderboard from "./components/Leaderboard";

export default function HomePage() {
  return (
    <div className="flex flex-col w-screen h-screen">
      <Header pageTitle="Home page" />
      <div className="grid grid-cols-3 grid-rows-2 ">
        <Table className="row-span-2 h-fit">
          <Table.Header>
            <Table.Row>
              <Table.HeaderCell>
                <Icon name="trophy" />
                Leaderboard
              </Table.HeaderCell>
            </Table.Row>
          </Table.Header>
          <Table.Body>
            <Table.Row>
              <Table.Cell>
                <Leaderboard />
              </Table.Cell>
            </Table.Row>
          </Table.Body>
        </Table>
        <Table celled className="col-span-2 !my-0">
          <Table.Header>
            <Table.Row>
              <Table.HeaderCell>Recent Projects</Table.HeaderCell>
            </Table.Row>
          </Table.Header>
          <Table.Body>
            <Table.Row>
              <Table.Cell>
                <ProjectsPreview />
              </Table.Cell>
            </Table.Row>
          </Table.Body>
        </Table>
        <Table celled className="col-span-2 !my-0">
          <Table.Header>
            <Table.Row>
              <Table.HeaderCell>Recent News</Table.HeaderCell>
            </Table.Row>
          </Table.Header>
          <Table.Body>
            <Table.Row>
              <Table.Cell>
                <NewsPreview />
              </Table.Cell>
            </Table.Row>
          </Table.Body>
        </Table>
      </div>
    </div>
  );
}
