import { useEffect, useState } from "react";
import { List } from "semantic-ui-react";
import { GiAcorn } from "react-icons/gi";

import { backendApi, handleLogError } from "../../misc/ApiMappings";
import { useAuth } from "../../misc/AuthContext";
import { emptyForms } from "../../misc/ApiForms";
import { Header } from "../../components";

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
  }, []);

  return (
    <div className="flex flex-col gap-2 w-screen">
      <Header pageTitle="Account settings" />
      <div className="flex justify-center text-3xl w-3/4 mt-12">
        <List>
          <List.Item>
            <List.Icon name="address card" />
            <List.Content>{user.legalName}</List.Content>
          </List.Item>
          <List.Item>
            <List.Icon name="users" />
            <List.Content>Username: {user.username}</List.Content>
          </List.Item>
          <List.Item>
            <List.Icon name="phone" />
            <List.Content>Telephone number: {user.phone}</List.Content>
          </List.Item>
          <List.Item>
            <List.Icon>
              <GiAcorn />
            </List.Icon>
            <List.Content>Balance: {balance}</List.Content>
          </List.Item>
        </List>
      </div>
    </div>
  );
}
