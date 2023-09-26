import { useEffect, useState } from "react";
import ListNews from "../../news/components/ListNews";
import { Link } from "react-router-dom";
import { Button } from "semantic-ui-react";
import { emptyForms } from "../../../misc/ApiForms";
import { backendApi } from "../../../misc/ApiMappings";

export default function NewsPreview() {
  const [newsList, setNewsList] = useState(emptyForms.newsOverview);

  useEffect(() => {
    fetchNewsPreview();
  }, []);

  const fetchNewsPreview = async () => {
    const response = await backendApi.getAllNewsArticles(0, 2);
    setNewsList(response.data);
  };
  return newsList.articles == [] ? (
    <div>loading...</div>
  ) : (
    <div className="bg-slate-300">
      <ListNews newsList={newsList} />
      <Link to={"/news"}>
        <Button fluid primary>
          View All News Articles
        </Button>
      </Link>
    </div>
  );
}
