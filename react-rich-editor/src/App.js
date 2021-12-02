import './App.css';
import Editor from "./rich-editor/editor";

function App() {
  return (
    <div className="App">
      <Editor onChange={console.log} />
    </div>
  );
}

export default App;
