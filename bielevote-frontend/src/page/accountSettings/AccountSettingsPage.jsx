import { useEffect, useState } from "react";
import { List } from "semantic-ui-react";

import { backendApi, handleLogError } from "../../misc/ApiMappings";
import { useAuth } from "../../misc/AuthContext";
import { emptyForms } from "../../misc/ApiForms";
import { Header } from "../../components";

export default function AccountSettingsPage() {
  const [user, setUser] = useState(emptyForms.user);
  const { getUser } = useAuth();
  useEffect(() => {
    async function fetchData() {
      try {
        const response = await backendApi.userInfo(getUser());
        setUser(response.data);
      } catch (error) {
        handleLogError(error);
      }
    }
    fetchData();
  }, []);

  return (
    <div className="flex flex-col gap-2 w-screen">
      <Header pageTitle="Account settings" />
      <List>
        <List.Item>
          <List.Icon name="users" />
          <List.Content>{user.username}</List.Content>
        </List.Item>
        <List.Item>
          {/* <List.Icon name="marker" /> */}
          <List.Content>{user.phone}</List.Content>
        </List.Item>
        <List.Item>
          {/* <List.Icon name="mail" /> */}
          <List.Content>
            <a href="mailto:jack@semantic-ui.com">jack@semantic-ui.com</a>
          </List.Content>
        </List.Item>
        <List.Item>
          <List.Icon name="linkify" />
          <List.Content>
            <a href="http://www.semantic-ui.com">semantic-ui.com</a>
          </List.Content>
        </List.Item>
      </List>
    </div>
  );
}
