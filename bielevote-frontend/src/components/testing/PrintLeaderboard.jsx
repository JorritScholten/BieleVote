import { useState } from "react";
import { backendApi } from "../../misc/ApiMappings";
import { Table } from "semantic-ui-react";

export default function PrintLeaderboard() {
  const [authResponse, setAuthResponse] = useState(null);
  async function getLeaderboard() {
    try {
      const res = await backendApi.getLeaderboard();
      setAuthResponse(res.data);
    } catch (error) {
      setAuthResponse(null);
    }
  }

  return (
    <div className="bg-slate-200 w-fit h-fit flex flex-col gap-2 p-2">
      <button className="bg-green-300 p-1" onClick={getLeaderboard}>
        get leaderboard
      </button>
      <div className="p-1">
        {/* test result: {JSON.stringify(authResponse, null, 2)} */}
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
