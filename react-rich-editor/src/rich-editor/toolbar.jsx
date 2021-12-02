import Toolbar from '@aomao/toolbar';

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

const ToolbarExample = ({ engine, items, className }) => {
	return (
		<Toolbar
			className={className}
			engine={engine}
			items={items || defaultItems}
		/>
	);
};

export default ToolbarExample;
