import { Link } from "react-router-dom";
import PropTypes from "prop-types";
import { Icon, Item } from "semantic-ui-react";
import { BiCategory } from "react-icons/bi";

import { formatDate } from "../../../components/Utils";

export default function ListNews({ newsList, hideCategory = false }) {
  return newsList.articles == [] ? (
    <div>loading...</div>
  ) : (
    <Item.Group relaxed>
      {newsList.articles.map((articlePreview) => (
        <Item key={articlePreview.id}>
          <Item.Content>
            <Item.Header>
              <Link to={"/news/" + articlePreview.id}>
                {articlePreview.title}
              </Link>
            </Item.Header>
            <Item.Meta>
              <Icon name="calendar alternate" />{" "}
              {formatDate(articlePreview.datePlaced)}
            </Item.Meta>
            <Item.Meta>
              <Icon name="user" /> {articlePreview.author}
            </Item.Meta>
            <Item.Description>{articlePreview.summaryPreview}</Item.Description>
            <Item.Extra className={hideCategory ? "!hidden" : ""}>
              <Icon className="relative [top:0.375rem;]">
                <BiCategory />
              </Icon>{" "}
              {articlePreview.category}
            </Item.Extra>
          </Item.Content>
        </Item>
      ))}
    </Item.Group>
  );
}

ListNews.propTypes = {
  newsList: PropTypes.object.isRequired,
  hideCategory: PropTypes.bool,
};
