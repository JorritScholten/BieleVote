import { useEffect, useState } from "react";
import { Grid, GridColumn, Icon, List, Table } from "semantic-ui-react";
import { BiBug } from "react-icons/bi";

import { backendApi, handleLogError } from "../../misc/ApiMappings";
import { useAuth } from "../../misc/AuthContext";
import { emptyForms } from "../../misc/ApiForms";
import { Header } from "../../components";
import UpdateUsernameForm from "./components/UpdateUsernameForm";

export default function AccountSettingsPage() {
  const [user, setUser] = useState(emptyForms.user);
  const [balance, setBalance] = useState(NaN);
  const { getUser } = useAuth();

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

  return (
    <div className="flex flex-col gap-2 w-screen">
      <Header pageTitle="Account settings" />
      <div className="flex justify-center text-3xl w-3/4 mt-12">
        <Table celled>
          <Table.Body>
            <Table.Row>
              <List.Icon name="address card" />
              <List.Content>Legal name: {user.legalName}</List.Content>
            </Table.Row>
            <Table.Row>
              <List.Icon name="user" />
              <List.Content>Username: {user.username}</List.Content>
            </Table.Row>
            <Table.Row>
              <List.Icon name="phone" />
              <List.Content>Telephone: {user.phone}</List.Content>
            </Table.Row>
            <Table.Row>
              <List.Icon>
                <Icon name="money" />
              </List.Icon>
              <List.Content>
                Balance: {balance}{" "}
                <Icon className="relative top-1">
                  <BiBug />
                </Icon>
              </List.Content>
            </Table.Row>
          </Table.Body>
          {/* <Table.Row>
            <UpdateUsernameForm />
          </Table.Row> */}
        </Table>
      </div>
    </div>
  );
}
