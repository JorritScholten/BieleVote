import { useState, useEffect } from "react";
import axios from "axios";
import { useParams } from "react-router-dom";
import { emptyForms } from "../../misc/ApiForms";

export default function NewsPage() {
  const [newsArticle, setNewsArticle] = useState(emptyForms.newsArticle);
  const { articleId } = useParams();

  useEffect(() => {
    async function getNewsArticle() {
      const response = await axios.get(
        `http://localhost:8080/api/v1/articles/${articleId}`
      );
      setNewsArticle(response.data);
      console.log(response.data);
    }
    getNewsArticle();
  }, [articleId]);

  return (
    <div>
      <div className="text-2xl">{newsArticle.title}</div>
      <p>{newsArticle.category}</p>
      <br></br>
      <div>{newsArticle.datePlaced}</div>
      <br></br>
      <div className="text-xl">{newsArticle.summary}</div>
      <br></br>
      <p>{newsArticle.content}</p>
      <br></br>
      <div>{newsArticle.author}</div>
    </div>
  );
}
