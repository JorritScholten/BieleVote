import { useEffect, useState } from "react";
import { Dropdown, Header, Table } from "semantic-ui-react";

import { backendApi, timeRanges } from "../../../misc/ApiMappings";

export default function Leaderboard() {
  const [authResponse, setAuthResponse] = useState(null);
  const [range, setRange] = useState(timeRanges.allTime);

  useEffect(() => {
    getLeaderboard();
  }, []);

  async function getLeaderboard(timeRange) {
    try {
      const res = await backendApi.getLeaderboard(timeRange);
      setAuthResponse(res.data);
    } catch (error) {
      setAuthResponse(null);
    }
  }
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
                getLeaderboard(newRange);
              }}
              simple
              item
            />
          </Header.Content>
        </Table.Header>
      </div>
      <div className="p-1">
        <Table basic="very" celled compact>
          <Table.Header>
            <Table.Row>
              <Table.HeaderCell collapsing>Rank</Table.HeaderCell>
              <Table.HeaderCell collapsing>Score</Table.HeaderCell>
              <Table.HeaderCell>Name</Table.HeaderCell>
            </Table.Row>
          </Table.Header>
          <Table.Body>
            {authResponse !== null ? (
              authResponse.map((score) => (
                <Table.Row key={score.rank}>
                  <Table.Cell verticalAlign="right">{score.rank}</Table.Cell>
                  <Table.Cell verticalAlign="center">{score.score}</Table.Cell>
                  <Table.Cell>{score.username}</Table.Cell>
                </Table.Row>
              ))
            ) : (
              <Table.Row>
                <Table.Cell />
                <Table.Cell />
              </Table.Row>
            )}
          </Table.Body>
        </Table>
      </div>
    </div>
  );
}
