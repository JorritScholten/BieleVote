import { useState } from "react";
import { backendApi, timeRanges } from "../../misc/ApiMappings";
import { Button, Dropdown, Table } from "semantic-ui-react";

export default function PrintLeaderboard() {
  const [authResponse, setAuthResponse] = useState(null);
  const [range, setRange] = useState(timeRanges.allTime);
  async function getLeaderboard(timeRange) {
    try {
      const res = await backendApi.getLeaderboard(timeRange);
      setAuthResponse(res.data);
      console.log(res.data);
    } catch (error) {
      setAuthResponse(null);
    }
  }
  const rangeOptions = [
    { key: 1, text: "All time", value: timeRanges.allTime },
    { key: 2, text: "Last year", value: timeRanges.lastYear },
    { key: 3, text: "Last month", value: timeRanges.lastMonth },
    { key: 4, text: "Last week", value: timeRanges.lastWeek },
  ];

  return (
    <div className="bg-slate-200 w-fit h-fit flex flex-col gap-2 p-2">
      <div className="p-1">
        <Button primary onClick={() => getLeaderboard(range)}>
          get leaderboard
        </Button>
        <Dropdown
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
      </div>
      <div className="p-1">
        <Table basic="very" celled collapsing>
          <Table.Header>
            <Table.Row>
              <Table.HeaderCell>Score</Table.HeaderCell>
              <Table.HeaderCell>Username</Table.HeaderCell>
            </Table.Row>
          </Table.Header>
          <Table.Body>
            {authResponse !== null ? (
              authResponse.map((score) => (
                <Table.Row key={score.username}>
                  <Table.Cell>{score.score}</Table.Cell>
                  <Table.Cell>{score.username}</Table.Cell>
                </Table.Row>
              ))
            ) : (
              <div />
            )}
          </Table.Body>
        </Table>
      </div>
    </div>
  );
}
