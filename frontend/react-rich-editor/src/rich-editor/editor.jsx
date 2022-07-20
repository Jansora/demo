import React, {useEffect, useRef, useState} from 'react';
import Engine, {$} from '@aomao/engine';
import {cards, pluginConfig, plugins} from './config.jsx';

import './index.css'
import 'antd/dist/antd.min.css'
import Toolbar from "./toolbar";

const defaultItems = [
    ['collapse'],
    ['undo', 'redo', 'paintformat', 'removeformat'],
    ['heading', 'fontfamily', 'fontsize'],
    ['bold', 'italic', 'strikethrough', 'underline', 'moremark'],
    ['fontcolor', 'backcolor'],
    ['alignment'],
    [
        'unorderedlist',
        'orderedlist',
        'tasklist',
        'indent',
        'line-height',
    ],
    ['link', 'quote', 'hr'],
];


const Editor = ({onChange}) => {

    //编辑器容器
    const ref = useRef(null);
    //引擎实例
    const [engine, setEngine] = useState();
    //编辑器内容
    const [content, setContent] = useState('Editing here!');

    useEffect(() => {

        if (!ref.current) return;
        //实例化引擎
        const engine = new Engine(ref.current, {
            plugins: plugins,
            cards: cards,
            config: {
                ...pluginConfig,
            },
        });
        //设置编辑器值
        engine.setValue(content);
        //监听编辑器值改变事件
        engine.on('change', (value) => {
            setContent(value);
            onChange && onChange(value)
            console.log(`value:${value}`);
        });
        //设置引擎实例
        setEngine(engine);
    }, []);

    // 点击编辑区域外的空白位置继续聚焦编辑器
    const wrapperMouseDown = (event) => {
        const {target} = event;
        console.log("wrapperMouseDown", event.target, ref.current)
        if (
            !target ||
            ['TEXTAREA', 'INPUT'].indexOf(target.nodeName) > -1
        )
            return;
        if (
            ref.current &&
            // ref.current.isFocus() &&
            $(target).closest('.editor-content').length === 0
        ) {
            event.preventDefault();
        }
    };
    // 编辑器区域单击在没有元素的位置，聚焦到编辑器
    const editorAreaClick = (event) => {
        const {target} = event;
        if (!target) return;
        if (ref.current && $(target).hasClass('editor-content')) {
            event.preventDefault();
        }
    };

    console.log("engine", engine)
    return (
        <div>
            <div>
                {engine && <Toolbar
                    engine={engine}
                    items={defaultItems}
                />}
            </div>
            <div className="editor-wrapper" onMouseDown={wrapperMouseDown}>
                <div className="editor-container">
                    <div
                        className="editor-content"
                        onMouseDown={editorAreaClick}
                    >
                        <div ref={ref}/>

                    </div>
                </div>
            </div>
        </div>
    );
}

export default Editor;
