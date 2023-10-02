import { Icon, Item } from "semantic-ui-react";
import PropTypes from "prop-types";
import { formatDate } from "../../../components/Utils";

export default function ListAccountRequests({ accountRequestList }) {
  return accountRequestList.accountRequests == [] ? (
    <div>loading...</div>
  ) : (
    <Item.Group relaxed>
      {accountRequestList.accountRequests.map((newAccount) => (
        <Item key={newAccount.id}>
          <Item.Content>
            <Item.Header>{newAccount.legalName}</Item.Header>
            <Item.Meta>
              <Icon name="calendar alternate" /> Requested:{" "}
              {formatDate(newAccount.dateRequested)}
            </Item.Meta>
            <Item.Description>{newAccount.username}</Item.Description>
            <Item.Description>{newAccount.phone}</Item.Description>
            <Item.Extra>User Role </Item.Extra>
          </Item.Content>
        </Item>
      ))}
    </Item.Group>
  );
}
ListAccountRequests.propTypes = {
  accountRequestList: PropTypes.object.isRequired,
};
