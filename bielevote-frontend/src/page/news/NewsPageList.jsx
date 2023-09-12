import { useState, useEffect } from "react";
import axios from "axios";
import { Link, useParams } from "react-router-dom";

export default function NewsPageList() {
  const [newsList, setNewsList] = useState([]);

  useEffect(() => {
    const getNewsArticleList = async () => {
      const allArticles = await axios.get(
        "http://localhost:8080/api/v1/articles"
      );
      setNewsList(allArticles.data);

      console.log(allArticles);
    };
    getNewsArticleList();
  }, []);

  return (
    <>
      {newsList.map((articlePreview) => (
        <div key={articlePreview.id}>
          <Link to={"/news/" + articlePreview.id}>
            <div className="text-2xl">{articlePreview.title}</div>
          </Link>
          <br></br>
          <div>{articlePreview.datePlaced}</div>
          <br></br>
          <div className="text-xl">{articlePreview.summaryPreview}</div>
          <br></br>
        </div>
      ))}
    </>
  );
}
