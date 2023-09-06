import { useState } from "react";

export default function NewsPage() {
  const emptyArticle = {
    title: "",
    summary: "",
    content: [],
    author: "",
    datePlaced: "2022-01-02",
    category: "",
    reaction: "",
  };
  const dummyArticle = {
    id: 1,
    title: "Story of Lorem",
    summary:
      "Lorem ipsum dolor sit amet consectetur adipisicing elit. Magnam, omnis.",
    content:
      "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed gravida, velit nec bibendum mattis, est libero pellentesque libero, a ullamcorper dolor lorem eu mauris. Proin a congue justo, non ultrices metus. Cras sit amet erat sit amet ligula efficitur vestibulum.",
    author: "John Doe",
    datePlaced: "2022-01-02",
    category: "Science",
    reaction: ["negative ", "neutral ", "positive"],
  };
  console.log(dummyArticle);
  const [news, setNews] = useState(emptyArticle);

  return (
    <>
      <h1>{dummyArticle.title}</h1>
      <p>{dummyArticle.category}</p>
      <br></br>
      <p>{dummyArticle.summary}</p>
      <br></br>
      <p>{dummyArticle.content}</p>
      <br></br>
      <div>
        <div>{dummyArticle.datePlaced}</div>
        <div>{dummyArticle.author}</div>
        <br></br>
        <div>{dummyArticle.reaction}</div>
      </div>
    </>
  );
}
