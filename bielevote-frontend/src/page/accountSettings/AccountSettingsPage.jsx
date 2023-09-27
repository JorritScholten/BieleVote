import { useEffect, useState } from "react";
import { Button, Icon, Popup, Table } from "semantic-ui-react";
import { BiBug, BiCheck, BiX } from "react-icons/bi";

import { backendApi, handleLogError } from "../../misc/ApiMappings";
import { useAuth } from "../../misc/AuthContext";
import { emptyForms } from "../../misc/ApiForms";
import { Header } from "../../components";
import { accountType } from "../../misc/NavMappings";

export default function AccountSettingsPage() {
  const [user, setUser] = useState(emptyForms.user);
  const [balance, setBalance] = useState(NaN);
  const { getUser, getAccountType } = useAuth();

  useEffect(() => {
    async function getBalance() {
      try {
        const res = await backendApi.getAccountBalance(getUser());
        setBalance(res.data);
      } catch (error) {
        setBalance(NaN);
      }
    }
    async function fetchData() {
      try {
        const response = await backendApi.userInfo(getUser());
        setUser(response.data);
      } catch (error) {
        handleLogError(error);
      }
    }
    fetchData();
    getBalance();
  }, [getUser]);

  async function toggleAnonymous() {
    try {
      const response = await backendApi.toggleAnonymousOnLeaderboard(getUser());
      setUser(response.data);
    } catch (error) {
      handleLogError(error);
    }
  }

  return (
    <div className="flex flex-col gap-8 w-screen">
      <Header pageTitle="Account settings" />
      <div className="grid grid-cols-5 text-3xl">
        <Table celled basic compact className="col-start-2 col-span-3">
          <Table.Body>
            <Table.Row>
              <Table.Cell collapsing content={<Icon name="address card" />} />
              <Table.Cell collapsing textAlign="right" content="Legal name" />
              <Table.Cell>{user.legalName}</Table.Cell>
            </Table.Row>
            <Table.Row>
              <Table.Cell content={<Icon name="user" />} />
              <Table.Cell textAlign="right" content="Username" />
              <Table.Cell>{user.username}</Table.Cell>
            </Table.Row>
            <Table.Row>
              <Table.Cell content={<Icon name="phone" />} />
              <Table.Cell textAlign="right" content="Telephone" />
              <Table.Cell>{user.phone}</Table.Cell>
            </Table.Row>
            <Popup
              disabled={getAccountType() !== accountType.citizen}
              trigger={
                <Table.Row disabled={getAccountType() !== accountType.citizen}>
                  <Table.Cell content={<Icon name="user secret" />} />
                  <Table.Cell textAlign="right" content="Anonymous" />
                  <Table.Cell className="flex flex-row items-center gap-5">
                    {user.anonymousOnLeaderboard ? <BiCheck /> : <BiX />}
                    <Button onClick={toggleAnonymous} content="Toggle" />
                  </Table.Cell>
                </Table.Row>
              }
              content="Appear as anonymous on the leaderboard."
            />
            <Table.Row>
              <Table.Cell content={<Icon name="money" />} />
              <Table.Cell textAlign="right" content="Balance" />
              <Table.Cell>
                {balance}{" "}
                <Icon className="relative top-1">
                  <BiBug />
                </Icon>
              </Table.Cell>
            </Table.Row>
          </Table.Body>
        </Table>
      </div>
    </div>
  );
}
