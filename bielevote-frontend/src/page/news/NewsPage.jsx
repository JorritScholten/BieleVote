import { useState, useEffect } from "react";
import axios from "axios";
import { Link, useParams } from "react-router-dom";
import { emptyForms } from "../../misc/ApiForms";
import Header from "../../components/Header";
import { IoReturnDownBack } from "react-icons/io5";
import { BsFillCalendarWeekFill } from "react-icons/bs";

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
    <>
      <Header pageTitle="News" />
      <div className="flex m-auto text-6xl ml-10">
        <Link to={"/news"}>
          <IoReturnDownBack />
        </Link>
      </div>
      <div className="flex justify-center">
        <div className="flex items-center place-self-center w-3/5">
          <div className="flex flex-col w-3/4">
            <div className="flex items-center justify-between">
              <div className="text-4xl font-bold">{newsArticle.title}</div>
              <div>{newsArticle.category}</div>
            </div>
            <div className="flex flex-row mt-5">
              <div className="mr-3 text-gray-600">{newsArticle.datePlaced}</div>
              <div className="flex items-center">
                <BsFillCalendarWeekFill />
              </div>
            </div>
            <div className="text-xl font-semibold mt-5 w-4/5">
              {newsArticle.summary}
            </div>
            <div className="text-xl mt-5">{newsArticle.content}</div>
            <div className="text-gray-600 mt-5">{newsArticle.author}</div>
          </div>
        </div>
      </div>
    </>
  );
}
