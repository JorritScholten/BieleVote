import { useEffect, useState } from "react";
import { Dropdown, Header, Pagination, Table } from "semantic-ui-react";

import {
  backendApi,
  handleLogError,
  timeRanges,
} from "../../../misc/ApiMappings";
import { emptyForms } from "../../../misc/ApiForms";

export default function Leaderboard() {
  const [range, setRange] = useState(timeRanges.allTime);
  const [leaderboardList, setLeaderboardList] = useState(
    emptyForms.leaderboardPage
  );
  const amountOfScores = 5;

  useEffect(() => {
    handlePageChange();
  }, [range]);

  const handlePageChange = async (event, value) => {
    try {
      const page = value != null ? value.activePage - 1 : 0;
      const res = await backendApi.getLeaderboard(range, page, amountOfScores);
      setLeaderboardList(res.data);
    } catch (error) {
      handleLogError(error);
    }
  };
  const rangeOptions = [
    { key: 1, text: "all time", value: timeRanges.allTime },
    { key: 2, text: "last year", value: timeRanges.lastYear },
    { key: 3, text: "last month", value: timeRanges.lastMonth },
    { key: 4, text: "last week", value: timeRanges.lastWeek },
  ];

  return (
    <div className="flex flex-col gap-2">
      <div className="p-1">
        <Table.Header as="h4">
          <Header.Content>
            Points earned{" "}
            <Dropdown
              header="Adjust time span"
              value={range}
              options={rangeOptions}
              onChange={(e) => {
                const newRange = rangeOptions.find(
                  (option) => option.text === e.target.innerText
                ).value;
                setRange(newRange);
              }}
              simple
              item
            />
          </Header.Content>
        </Table.Header>
      </div>
      <div className="p-1">
        <Table basic="very" celled striped>
          <Table.Header>
            <Table.Row>
              <Table.HeaderCell textAlign="right">Rank</Table.HeaderCell>
              <Table.HeaderCell textAlign="center">Name</Table.HeaderCell>
              <Table.HeaderCell>Score</Table.HeaderCell>
            </Table.Row>
          </Table.Header>
          <Table.Body>
            {leaderboardList.scores != [] ? (
              leaderboardList.scores.map((score) => (
                <Table.Row key={score.rank}>
                  <Table.Cell textAlign="right">{score.rank}</Table.Cell>
                  <Table.Cell textAlign="center">{score.name}</Table.Cell>
                  <Table.Cell>{score.score}</Table.Cell>
                </Table.Row>
              ))
            ) : (
              <Table.Row>
                <Table.Cell />
                <Table.Cell />
                <Table.Cell />
              </Table.Row>
            )}
          </Table.Body>
          <Table.Footer fullWidth>
            <Table.Row>
              <Table.HeaderCell colSpan="3">
                <div className="flex justify-center">
                  <Pagination
                    defaultActivePage={1}
                    activePage={leaderboardList.currentPage + 1}
                    totalPages={leaderboardList.totalPages}
                    onPageChange={handlePageChange}
                  />
                </div>
              </Table.HeaderCell>
            </Table.Row>
          </Table.Footer>
        </Table>
      </div>
    </div>
  );
}
