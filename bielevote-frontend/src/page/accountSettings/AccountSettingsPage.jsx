import { useEffect, useState } from "react";
import { Icon, Table } from "semantic-ui-react";
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
          {/* <Table.Row>
            <UpdateUsernameForm />
          </Table.Row> */}
        </Table>
      </div>
    </div>
  );
}
