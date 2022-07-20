import React, {useState} from 'react';
import {Editor, Viewer} from '@bytemd/react';
import gfm from '@bytemd/plugin-gfm';
import frontmatter from "@bytemd/plugin-frontmatter"
import gemoji from "@bytemd/plugin-gemoji"

import highlight from "@bytemd/plugin-highlight"
import image from "@bytemd/plugin-import-image"
import math from "@bytemd/plugin-math"
import zoom from "@bytemd/plugin-medium-zoom"
import mermaid from "@bytemd/plugin-mermaid"
import vega from "@bytemd/plugin-vega"

import 'bytemd/dist/index.css'
import 'highlight.js/styles/vs.css';


const plugins = [
    gfm(),
    frontmatter(),
    gemoji(), highlight(),
    image({
        async upload(files) {
            // TODO: upload image files and return urls
            return ['url']
        }
    }),
    math(), zoom(), mermaid(), vega(),
    // Add more plugins here
];

const App = () => {
    const [value, setValue] = useState('---\n' +
        '# frontmatter: https://jekyllrb.com/docs/front-matter/\n' +
        'layout: post\n' +
        'title: Blogging Like a Hacker\n' +
        '---\n' +
        '\n' +
        '21324 \n' +
        '## Markdown Basic Syntax\n' +
        '\n' +
        'I just love **bold text**. Italicized text is the _cat\'s meow_. At the command prompt, type `nano`.\n' +
        '\n' +
        'My favorite markdown editor is [ByteMD](https://github.com/bytedance/bytemd).\n' +
        '\n' +
        '1. First item\n' +
        '2. Second item\n' +
        '3. Third item\n' +
        '\n' +
        '> Dorothy followed her through many of the beautiful rooms in her castle.\n' +
        '\n' +
        '```js\n' +
        'import { Editor, Viewer } from \'bytemd\';\n' +
        'import gfm from \'@bytemd/plugin-gfm\';\n' +
        '\n' +
        'const plugins = [\n' +
        '  gfm(),\n' +
        '  // Add more plugins here\n' +
        '];\n' +
        '\n' +
        'const editor = new Editor({\n' +
        '  target: document.body, // DOM to render\n' +
        '  props: {\n' +
        '    value: \'\',\n' +
        '    plugins,\n' +
        '  },\n' +
        '});\n' +
        '\n' +
        'editor.on(\'change\', (e) => {\n' +
        '  editor.$set({ value: e.detail.value });\n' +
        '});\n' +
        '```\n' +
        '\n' +
        '## GFM Extended Syntax\n' +
        '\n' +
        '~~The world is flat.~~ We now know that the world is round.\n' +
        '\n' +
        '| Syntax    | Description |\n' +
        '| --------- | ----------- |\n' +
        '| Header    | Title       |\n' +
        '| Paragraph | Text        |\n' +
        '\n' +
        '## Math Equation\n' +
        '\n' +
        'Inline math equation: $a+b$\n' +
        '\n' +
        '$$\n' +
        '\\displaystyle \\left( \\sum_{k=1}^n a_k b_k \\right)^2 \\leq \\left( \\sum_{k=1}^n a_k^2 \\right) \\left( \\sum_{k=1}^n b_k^2 \\right)\n' +
        '$$\n' +
        '\n' +
        '## Mermaid Diagrams\n' +
        '\n' +
        '```mermaid\n' +
        'graph TD;\n' +
        '  A-->B;\n' +
        '  A-->C;\n' +
        '  B-->D;\n' +
        '  C-->D;\n' +
        '```\n' +
        '\n' +
        '## Footnotes\n' +
        '\n' +
        'Here\'s a simple footnote,[^1] and here\'s a longer one.[^bignote]\n' +
        '\n' +
        '[^1]: This is the first footnote.\n' +
        '[^bignote]: Here\'s one with multiple paragraphs and code.\n' +
        '\n' +
        '    Indent paragraphs to include them in the footnote.\n' +
        '\n' +
        '    `{ my code }`\n' +
        '\n' +
        '    Add as many paragraphs as you like.\n');

    return (
        <>
            <Editor
                style={{height: '100vh'}}
                value={value}
                plugins={plugins}
                onChange={(v) => {
                    setValue(v);
                }}
            />
            <Viewer
                value={value}
                plugins={plugins}
            >

            </Viewer>
        </>
    );
};
export default App;
