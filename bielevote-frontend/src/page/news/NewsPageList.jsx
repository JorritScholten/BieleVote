import { useState, useEffect } from "react";
import axios from "axios";
import { Link } from "react-router-dom";
import Header from "../../components/Header";

import {
  BsFillArrowLeftSquareFill,
  BsFillArrowRightSquareFill,
} from "react-icons/bs";
import { BsFillCalendarWeekFill } from "react-icons/bs";
import { IoReturnDownBack } from "react-icons/io5";
import ReactPaginate from "react-paginate";

export default function NewsPageList() {
  const [newsList, setNewsList] = useState([]);

  const [currentPage, setCurrentPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
  const itemsPerPage = 10;

  useEffect(() => {
    const getNewsArticleList = async () => {
      const response = await axios.get("http://localhost:8080/api/v1/articles");
      setNewsList(response.data);
      setTotalPages(Math.ceil(response.data.length / itemsPerPage));
      console.log(response);
    };
    getNewsArticleList();
  }, []);

  const startIndex = currentPage * itemsPerPage;
  const endIndex = startIndex + itemsPerPage;
  const subset = newsList.slice(startIndex, endIndex);

  const handlePageChange = (selectedPage) => {
    setCurrentPage(selectedPage.selected);
  };

  return (
    <>
      <Header pageTitle="News" />
      <div className="flex m-auto text-6xl ml-10">
        <Link to={"/"}>
          <IoReturnDownBack />
        </Link>
      </div>
      <div className="flex items-center justify-center">
        <div className="flex flex-col  w-3/5">
          {subset.map((articlePreview) => (
            <div className="p-3 flex flex-col" key={articlePreview.id}>
              <Link to={"/news/" + articlePreview.id}>
                <div className="text-3xl text-blue-700 font-bold underline">
                  {articlePreview.title}
                </div>
              </Link>
              <div className="flex flex-row">
                <div className="mr-3 text-gray-600">
                  {articlePreview.datePlaced}
                </div>
                <div className="flex items-center">
                  <BsFillCalendarWeekFill />
                </div>
              </div>
              <div className="text-lg text-gray-800 font-semibold">
                {articlePreview.summaryPreview}
              </div>
            </div>
          ))}
          <div className="flex justify-center">
            <ReactPaginate
              className="flex flex-row"
              previousLabel={<BsFillArrowLeftSquareFill />}
              nextLabel={<BsFillArrowRightSquareFill />}
              breakLabel={"..."}
              pageCount={totalPages}
              onPageChange={handlePageChange}
              forcePage={currentPage}
            />
          </div>
        </div>
      </div>
    </>
  );
}
