import { useState, useEffect } from "react";
import axios from "axios";
import { useParams } from "react-router-dom";

export default function NewsPage() {
  const { articleId } = useParams();
  const [newsArticle, setNewsArticle] = useState({});

  const getNewsArticle = async () => {
    const articleData = await axios.get(
      `http://localhost:8080/api/v1/articles/${articleId}`
    );
    setNewsArticle(articleData.data);
    console.log(articleData);
  };
  useEffect(() => {
    getNewsArticle();
  }, []);

  return (
    <>
      {newsArticle.map((article) => (
        <>
          <div className="text-2xl">{article.title}</div>
          <p>{article.category}</p>
          <br></br>
          <div>{article.datePlaced}</div>
          <br></br>
          <div className="text-xl">{article.summaryPreview}</div>
          <br></br>
          {/* <p>{item.content}</p>
        <br></br>
          <div>{item.author}</div> */}
        </>
      ))}
    </>
  );
}
