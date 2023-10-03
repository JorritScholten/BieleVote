import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { Button } from "semantic-ui-react";

import ListNews from "../../news/components/ListNews";
import { emptyForms } from "../../../misc/ApiForms";
import { backendApi, handleLogError } from "../../../misc/ApiMappings";

export default function NewsPreview() {
  const [newsList, setNewsList] = useState(emptyForms.newsOverview);

  useEffect(() => {
    async function fetchNewsPreview() {
      try {
        const response = await backendApi.getAllNewsArticles(0, 2);
        setNewsList(response.data);
      } catch (error) {
        handleLogError(error);
      }
    }
    fetchNewsPreview();
  }, []);

  return newsList.articles == [] ? (
    <div>loading...</div>
  ) : (
    <div>
      <ListNews newsList={newsList} hideCategory={true} />
      <Link to={"/news"}>
        <Button fluid primary>
          View All News Articles
        </Button>
      </Link>
    </div>
  );
}
