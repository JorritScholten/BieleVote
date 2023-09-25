import { BiBug } from "react-icons/bi";
import { Icon, Item } from "semantic-ui-react";
import PropTypes from "prop-types";
import { formatDate } from "../../../components/Utils";

export default function ListRewardsPurchased({ transactionList }) {
  return transactionList.transactions == [] ? (
    <div>loading...</div>
  ) : (
    <Item.Group relaxed>
      {transactionList.transactions.map((transaction) => (
        <Item key={transaction.id}>
          <Item.Content>
            <Item.Header>{transaction.reward.name}</Item.Header>
            <Item.Meta>
              <Icon name="calendar alternate" /> Purchased:{" "}
              {formatDate(transaction.date)}
            </Item.Meta>
            <Item.Description>
              {transaction.reward.description}
            </Item.Description>
            <Item.Extra>
              Cost: {transaction.amount * -1}{" "}
              <Icon>
                <div className="flex items-center">
                  <BiBug />
                </div>
              </Icon>
            </Item.Extra>
          </Item.Content>
        </Item>
      ))}
    </Item.Group>
  );
}
ListRewardsPurchased.propTypes = {
  transactionList: PropTypes.object.isRequired,
};
