import { Button, Dropdown, Icon, Item } from "semantic-ui-react";
import PropTypes from "prop-types";
import { formatDate } from "../../../components/Utils";
import { useState } from "react";
import { backendApi, handleLogError } from "../../../misc/ApiMappings";
import { useAuth } from "../../../misc/AuthContext";
import { HttpStatusCode } from "axios";

export default function ListAccountRequests({
  accountRequestList,
  setVersion,
}) {
  const [userRole, setUserRole] = useState("CITIZEN");
  const { getUser } = useAuth();

  const userRoles = [
    {
      key: 1,
      text: "Citizen",
      value: "CITIZEN",
    },
    {
      key: 2,
      text: "Municipal",
      value: "MUNICIPAL",
    },
    {
      key: 3,
      text: "Administrator",
      value: "ADMINISTRATOR",
    },
  ];

  const createAccount = async (accountRequestId) => {
    try {
      const response = await backendApi.createNewAccount(
        accountRequestId,
        userRole,
        getUser()
      );
      if (response.status === HttpStatusCode.Created) {
        setVersion((v) => (v = v + 1));
      }
    } catch (error) {
      handleLogError(error);
    }
  };

  return accountRequestList.accountRequests == [] ? (
    <div>loading...</div>
  ) : (
    <Item.Group relaxed>
      {accountRequestList.accountRequests.map((accountRequest) => (
        <Item key={accountRequest.id}>
          <Item.Content>
            <Item.Header>
              <Icon name="calendar alternate" /> Requested:{" "}
              {formatDate(accountRequest.dateRequested)}
            </Item.Header>
            <Item.Description>
              Legal name: {accountRequest.legalName}
            </Item.Description>
            <Item.Description>
              Username: {accountRequest.username}
            </Item.Description>
            <Item.Description>
              Phone number: {accountRequest.phone}
            </Item.Description>
            <Item.Extra>
              <Dropdown
                placeholder="Select Role"
                fluid
                selection
                defaultValue={"CITIZEN"}
                options={userRoles}
                onChange={(e) => {
                  console.log(e);
                  setUserRole(
                    userRoles.find(
                      (option) => option.text === e.target.innerText
                    ).value
                  );
                }}
              />
            </Item.Extra>
            <Button.Group fluid>
              <Button
                positive
                onClick={() => createAccount(accountRequest.id)}
                content="Allow"
              />
              <Button
                negative
                //   onClick={() => moderateStatus(projectStatus.denied)}
                content="Deny"
              />
            </Button.Group>
          </Item.Content>
        </Item>
      ))}
    </Item.Group>
  );
}
ListAccountRequests.propTypes = {
  accountRequestList: PropTypes.object.isRequired,
  setVersion: PropTypes.func.isRequired,
};
