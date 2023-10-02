import { Button, Dropdown, Icon, Item } from "semantic-ui-react";
import PropTypes from "prop-types";
import { formatDate } from "../../../components/Utils";
import { useState } from "react";

export default function ListAccountRequests({ accountRequestList }) {
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

  return accountRequestList.accountRequests == [] ? (
    <div>loading...</div>
  ) : (
    <Item.Group relaxed>
      {accountRequestList.accountRequests.map((newAccount) => (
        <Item key={newAccount.id}>
          <Item.Content>
            <Item.Header>
              <Icon name="calendar alternate" /> Requested:{" "}
              {formatDate(newAccount.dateRequested)}
            </Item.Header>
            <Item.Description>
              Legal name: {newAccount.legalName}
            </Item.Description>
            <Item.Description>Username: {newAccount.username}</Item.Description>
            <Item.Description>
              Phone number: {newAccount.phone}
            </Item.Description>
            <Item.Extra>
              <Dropdown
                placeholder="Select Role"
                fluid
                selection
                defaultValue={"CITIZEN"}
                options={userRoles}
              />
            </Item.Extra>
            <Button.Group fluid>
              <Button
                positive
                //   onClick={() => moderateStatus(projectStatus.active)}
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
};
